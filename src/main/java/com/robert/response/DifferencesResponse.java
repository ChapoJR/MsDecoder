package com.robert.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Roberto Armenta Response Class which provides the JSON structure in
 * my GET service to get the comparison between two structures
 */
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "DifferencesResponse", description = "Response structure when the comparation was success")
public @Data
class DifferencesResponse {

    @ApiModelProperty(name = "length", position = 1, example = "Length is equal", value = "This property describes if the length in both sides is equal o not", required = true)
    private String length;
    @ApiModelProperty(name = "leftDifference", position = 2, example = "firstName=Tom, lastName=Cruise", value = "This property shows the fields that just exist in the left side", required = false)
    private String leftDifference;
    @ApiModelProperty(name = "rightDifference", position = 3, example = "data=Click Here, size=36.0", value = "This property shows the fields that just exist in the right side", required = false)
    private String rightDifference;
    @ApiModelProperty(name = "valueDifference", position = 4, example = "hOffset=250.0, vOffset=250.0", value = "This property shows the fields that exist in both sides but with different value", required = false)
    private String valueDifference;
}
