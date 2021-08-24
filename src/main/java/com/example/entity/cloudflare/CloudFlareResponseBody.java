package com.example.entity.cloudflare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author RanGe
 * @date 20/01/01/001 22:47
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CloudFlareResponseBody {

    private String cookie;

    private String headers;

    private String entity;

}
