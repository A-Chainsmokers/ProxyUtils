async function gatherResponse(response) {
    const result = {};
    const {headers} = response;
    const contentType = headers.get("content-type") || ""
    if (contentType.includes("application/json")) {
        result.entity = JSON.stringify(await response.json());
    } else if (contentType.includes("application/text")) {
        result.entity = await response.text()
    } else if (contentType.includes("text/html")) {
        result.entity = await response.text()
    } else {
        result.entity = await response.text()
    }
    return JSON.stringify(result);
}

function getQueryVariable(variable, url) {
    return url.split('?param=')[1];
}

function parseUrl(data) {
    data = decodeURIComponent(data)
    let arr = data.split("");
    if (arr[arr.length - 1].indexOf("/") !== -1) {
        return data.substr(0, data.length - 1);
    } else {
        return data;
    }
}

function parseData(data) {

    data = JSON.parse(data);

    const init = {}
    for (let key in data) {
        if (key === "form" && data['body']) {
            if (data[key] === true) {
                let formData = new FormData();
                for (let bodyKey in data['body']) {
                    formData.append(bodyKey, data['body'][bodyKey]);
                }
                init.body = formData;
            } else {
                init.body = JSON.stringify(data['body']);
            }
        } else {
            if (key === 'body') {
                continue;
            }
            init[key] = data[key];
        }
    }
    return init;
}

async function handleRequest(param) {
    let init = parseData(param);
    const response = await fetch(init.url, init)
    const results = await gatherResponse(response)
    return new Response(results, init)
}

addEventListener("fetch", event => {
    const data = getQueryVariable("param", event.request.url);
    const param = parseUrl(data);
    return event.respondWith(handleRequest(param))
})

