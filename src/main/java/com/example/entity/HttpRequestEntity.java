package com.example.entity;

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
public class HttpRequestEntity {

    private String cookie;

    private String headers;

    private String entity;

}
