package com.robert.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.robert.enums.HashJSONKey;
import com.robert.exception.FunctionJSONException;
import com.robert.request.StoreJSONRequest;
import com.robert.response.GenericResponse;
import com.robert.service.impl.OperationDecoderWaesImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperationDecoderWaesImplTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	OperationDecoderWaesImpl saveJSONService;

	@Test
	@Order(1)
	@DisplayName("Testing saveJSONNullIdMethod")
	public void saveJSONNullId() throws FunctionJSONException {
		Assertions.assertThrows(FunctionJSONException.class, () -> {
			saveJSONService.storeJSON(null, HashJSONKey.LEFT,
					"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
		});
	}

	@Test
	@Order(2)
	@DisplayName("Testing saveJSONEmptyIdMethod")
	public void saveJSONEmptyId() throws FunctionJSONException {
		Assertions.assertThrows(FunctionJSONException.class, () -> {
			saveJSONService.storeJSON("  ", HashJSONKey.LEFT,
					"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=");
		});
	}

	@Test
	@DisplayName("Testing saveJSONMethod LeftSide")
	@Order(3)
	public void saveJSONLeftTest() throws FunctionJSONException {
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=") instanceof GenericResponse);
		getAllDifferences();
	}

	@Test
	@DisplayName("Testing saveJSONMethod RightSide")
	@Order(4)
	public void saveJSONRightTest() throws FunctionJSONException {
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.RIGHT,
				"ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=") instanceof GenericResponse);
	}

	@Test
	@DisplayName("Testing getJSONDifferenceMethod")
	@Order(5)
	public void getJSONDifferenceTest() throws FunctionJSONException {
		assertNotNull(saveJSONService.getJSONDifference("Test"));
	}

	@Test
	@DisplayName("Testing saveJSONMethod Omiteed ")
	@Order(6)
	public void saveJSONOmittedDataTest() throws FunctionJSONException {
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=") instanceof GenericResponse);
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.RIGHT,
				"ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=") instanceof GenericResponse);
	}

	@Test
	@DisplayName("Testing saveEqualJSONSMethod")
	@Order(7)
	public void saveEqualJSONSMethod() throws FunctionJSONException {
		assertTrue(saveJSONService.storeJSON("Equal", HashJSONKey.LEFT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=") instanceof GenericResponse);
		assertTrue(saveJSONService.storeJSON("Equal", HashJSONKey.RIGHT,
				"ewogICAgInRva2VuIjogIjEyMyRkaGdqdHUiLAogICAgImNvbW1lbnQiOiAiVGVzdDEiCn0=") instanceof GenericResponse);
	}

	@Test
	@DisplayName("Testing saveJSONMethod Override")
	@Order(8)
	public void saveJSONOverrideStoredDataTest() throws FunctionJSONException {
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.RIGHT,
				"ewogICAgIml0ZW1zIjogWwogICAgICAgIHsKICAgICAgICAgICAgImlkIjogNzA0LAogICAgICAgICAgICAidmFsb3IiOiAiMTAwQ0MiLAogICAgICAgICAgICAibWFyY2EiOiAiQ0FSQUJFTEEiCiAgICAgICAgfSwKICAgICAgICB7CiAgICAgICAgICAgICJpZCI6IDY1NCwKICAgICAgICAgICAgInZhbG9yIjogIjEyMDAgQ1VTVE9NIiwKICAgICAgICAgICAgIm1hcmNhIjogIkhBUkxFWS1EQVZJRFNPTiIKICAgICAgICB9LAogICAgICAgIHsKICAgICAgICAgICAgImlkIjogNzA3LAogICAgICAgICAgICAidmFsb3IiOiAiMTI1Q0MiLAogICAgICAgICAgICAibWFyY2EiOiAiQ0FSQUJFTEEiCiAgICAgICAgfQpdCn0=") instanceof GenericResponse);
		assertTrue(saveJSONService.storeJSON("Test", HashJSONKey.LEFT,
				"ewoiY29tbWVudCIgOiAiVGVzdCIsCiJjb2RlIiA6IDAwCn0=") instanceof GenericResponse);
	}

	@Test
	@DisplayName("Testing getAllDifferencesMethod Override")
	@Order(9)
	public void getAllDifferences() throws FunctionJSONException {
		assertTrue(saveJSONService.getAllDifferences() instanceof List<?>);
	}

	/**
	 * Method to Test the full flow of the four services (Left, Right, Get, GetAll)
	 */
	@Test
	@DisplayName("Integration Test  FullFlowMethod")
	@Order(10)
	public void fullFlowTest() {
		String urlService = "http://localhost:" + port;
		HttpHeaders headersLeft = new HttpHeaders();
		headersLeft.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StoreJSONRequest> requestBody = new HttpEntity<>(new StoreJSONRequest(
				"ewogICAgImdsb3NzYXJ5IjogewogICAgICAgICJ0aXRsZSI6ICJleGFtcGxlIGdsb3NzYXJ5IiwKCQkiR2xvc3NEaXYiOiB7CiAgICAgICAgICAgICJ0aXRsZSI6ICJTIiwKCQkJIkdsb3NzTGlzdCI6IHsKICAgICAgICAgICAgICAgICJHbG9zc0VudHJ5IjogewogICAgICAgICAgICAgICAgICAgICJJRCI6ICJTR01MIiwKCQkJCQkiU29ydEFzIjogIlNHTUwiLAoJCQkJCSJHbG9zc1Rlcm0iOiAiU3RhbmRhcmQgR2VuZXJhbGl6ZWQgTWFya3VwIExhbmd1YWdlIiwKCQkJCQkiQWNyb255bSI6ICJTR01MIiwKCQkJCQkiQWJicmV2IjogIklTTyA4ODc5OjE5ODYiLAoJCQkJCSJHbG9zc0RlZiI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInBhcmEiOiAiQSBtZXRhLW1hcmt1cCBsYW5ndWFnZSwgdXNlZCB0byBjcmVhdGUgbWFya3VwIGxhbmd1YWdlcyBzdWNoIGFzIERvY0Jvb2suIiwKCQkJCQkJIkdsb3NzU2VlQWxzbyI6IFsiR01MIiwgIlhNTCJdCiAgICAgICAgICAgICAgICAgICAgfSwKCQkJCQkiR2xvc3NTZWUiOiAibWFya3VwIgogICAgICAgICAgICAgICAgfQogICAgICAgICAgICB9CiAgICAgICAgfQogICAgfQp9"),
				headersLeft);
		ResponseEntity<Object> responseBody = this.restTemplate.exchange(urlService + "/v1/diff/Integration/left",
				HttpMethod.POST, requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		requestBody = new HttpEntity<>(new StoreJSONRequest(
				"eyJ3aWRnZXQiOiB7CiAgICAiZGVidWciOiAib24iLAogICAgIndpbmRvdyI6IHsKICAgICAgICAidGl0bGUiOiAiU2FtcGxlIEtvbmZhYnVsYXRvciBXaWRnZXQiLAogICAgICAgICJuYW1lIjogIm1haW5fd2luZG93IiwKICAgICAgICAid2lkdGgiOiA1MDAsCiAgICAgICAgImhlaWdodCI6IDUwMAogICAgfSwKICAgICJpbWFnZSI6IHsgCiAgICAgICAgInNyYyI6ICJJbWFnZXMvU3VuLnBuZyIsCiAgICAgICAgIm5hbWUiOiAic3VuMSIsCiAgICAgICAgImhPZmZzZXQiOiAyNTAsCiAgICAgICAgInZPZmZzZXQiOiAyNTAsCiAgICAgICAgImFsaWdubWVudCI6ICJjZW50ZXIiCiAgICB9LAogICAgInRleHQiOiB7CiAgICAgICAgImRhdGEiOiAiQ2xpY2sgSGVyZSIsCiAgICAgICAgInNpemUiOiAzNiwKICAgICAgICAic3R5bGUiOiAiYm9sZCIsCiAgICAgICAgIm5hbWUiOiAidGV4dDEiLAogICAgICAgICJoT2Zmc2V0IjogMjUwLAogICAgICAgICJ2T2Zmc2V0IjogMTAwLAogICAgICAgICJhbGlnbm1lbnQiOiAiY2VudGVyIiwKICAgICAgICAib25Nb3VzZVVwIjogInN1bjEub3BhY2l0eSA9IChzdW4xLm9wYWNpdHkgLyAxMDApICogOTA7IgogICAgfQp9fSAgIA=="),
				headersLeft);
		responseBody = this.restTemplate.exchange(urlService + "/v1/diff/Integration/right", HttpMethod.POST,
				requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		responseBody = this.restTemplate.exchange(urlService + "/v1/diff/Integration", HttpMethod.GET, requestBody,
				Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		responseBody = this.restTemplate.exchange(urlService + "/v1/diff", HttpMethod.GET, requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
	}
}
