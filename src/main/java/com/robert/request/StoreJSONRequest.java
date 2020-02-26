package com.robert.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Roberto Armenta 
 * Request Class Body which is the Input in my POST
 * services to store a new JSON encode String
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreJSONRequest {

    @ApiModelProperty(value = "This is the input Encode JSON Base 64 to store ", required = true)
    private String jsonObject;
}
