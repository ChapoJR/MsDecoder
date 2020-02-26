package com.robert.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Roberto Armenta
 * Class which generates generic responses for the services especially  when the an exception is throwed
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GenericResponse", description = "Generic response to show the result of generic operations")
public @Data class GenericResponse {
	@ApiModelProperty(name = "message", position = 1, example = "JSON stored", value = "This property show a message from the result of the proccess", required = true)
	private String message;
	@ApiModelProperty(name = "date", position = 1, example = "2020/02/25 05:13:24", value = "This property show the date of the request", required = true)
	private String date;

	public GenericResponse(String message) {
		this.message = message;
		this.date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
	}
}
