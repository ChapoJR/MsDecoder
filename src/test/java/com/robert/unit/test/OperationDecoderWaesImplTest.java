package com.robert.unit.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.robert.enums.HashJSONKey;
import com.robert.exception.FunctionJSONException;
import com.robert.service.impl.OperationDecoderWaesImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperationDecoderWaesImplTest {

	@Autowired
	OperationDecoderWaesImpl saveJSONService;

	@Test
	@DisplayName("Testing saveJSONMethod LeftSide")
	@Order(1)
	public void saveJSONLeftTest() throws FunctionJSONException {
		saveJSONService.storeJSON("Test", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
	}

	@Test
	@DisplayName("Testing saveJSONMethod RightSide")
	@Order(2)
	public void saveJSONRightTest() throws FunctionJSONException {
		saveJSONService.storeJSON("Test", HashJSONKey.RIGHT, "ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=");
	}

	@Test
	@DisplayName("Testing getJSONDifferenceMethod")
	@Order(3)
	public void getJSONDifferenceTest() throws FunctionJSONException {
		assertNotNull(saveJSONService.getJSONDifference("Test"));
	}

	@Test
	@DisplayName("Testing saveEqualJSONSMethod")
	@Order(4)
	public void saveEqualJSONSMethod() throws FunctionJSONException {
		saveJSONService.storeJSON("Equal", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
		saveJSONService.storeJSON("Equal", HashJSONKey.RIGHT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
	}

	@Test
	@DisplayName("Testing saveJSONMethod Omiteed ")
	@Order(5)
	public void saveJSONOmittedDataTest() throws FunctionJSONException {
		saveJSONService.storeJSON("Test", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
		saveJSONService.storeJSON("Test", HashJSONKey.RIGHT, "ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=");
	}

	@Test
	@DisplayName("Testing saveJSONMethod Override")
	@Order(6)
	public void saveJSONOverrideStoredDataTest() throws FunctionJSONException {
		saveJSONService.storeJSON("Test", HashJSONKey.RIGHT,
				"ewogICAgIml0ZW1zIjogWwogICAgICAgIHsKICAgICAgICAgICAgImlkIjogNzA0LAogICAgICAgICAgICAidmFsb3IiOiAiMTAwQ0MiLAogICAgICAgICAgICAibWFyY2EiOiAiQ0FSQUJFTEEiCiAgICAgICAgfSwKICAgICAgICB7CiAgICAgICAgICAgICJpZCI6IDY1NCwKICAgICAgICAgICAgInZhbG9yIjogIjEyMDAgQ1VTVE9NIiwKICAgICAgICAgICAgIm1hcmNhIjogIkhBUkxFWS1EQVZJRFNPTiIKICAgICAgICB9LAogICAgICAgIHsKICAgICAgICAgICAgImlkIjogNzA3LAogICAgICAgICAgICAidmFsb3IiOiAiMTI1Q0MiLAogICAgICAgICAgICAibWFyY2EiOiAiQ0FSQUJFTEEiCiAgICAgICAgfQpdCn0=");
		saveJSONService.storeJSON("Test", HashJSONKey.LEFT, "ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=");
	}
}
