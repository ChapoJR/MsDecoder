package com.robert.integration.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import com.robert.request.StoreJSONRequest;

/**
 *
 * @author Roberto Armenta Class which I realize the Integration test between my
 *         three services
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DecoderControllerWaesImplTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Method to Test the full flow of the three services (Left, Right, Get)
	 */
	@Test
	@DisplayName("Testing FullFlowMethod")
	public void fullFlowTest() {
		HttpHeaders headersLeft = new HttpHeaders();
		headersLeft.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<StoreJSONRequest> requestBody = new HttpEntity<>(new StoreJSONRequest(
				"ewogICAgImdsb3NzYXJ5IjogewogICAgICAgICJ0aXRsZSI6ICJleGFtcGxlIGdsb3NzYXJ5IiwKCQkiR2xvc3NEaXYiOiB7CiAgICAgICAgICAgICJ0aXRsZSI6ICJTIiwKCQkJIkdsb3NzTGlzdCI6IHsKICAgICAgICAgICAgICAgICJHbG9zc0VudHJ5IjogewogICAgICAgICAgICAgICAgICAgICJJRCI6ICJTR01MIiwKCQkJCQkiU29ydEFzIjogIlNHTUwiLAoJCQkJCSJHbG9zc1Rlcm0iOiAiU3RhbmRhcmQgR2VuZXJhbGl6ZWQgTWFya3VwIExhbmd1YWdlIiwKCQkJCQkiQWNyb255bSI6ICJTR01MIiwKCQkJCQkiQWJicmV2IjogIklTTyA4ODc5OjE5ODYiLAoJCQkJCSJHbG9zc0RlZiI6IHsKICAgICAgICAgICAgICAgICAgICAgICAgInBhcmEiOiAiQSBtZXRhLW1hcmt1cCBsYW5ndWFnZSwgdXNlZCB0byBjcmVhdGUgbWFya3VwIGxhbmd1YWdlcyBzdWNoIGFzIERvY0Jvb2suIiwKCQkJCQkJIkdsb3NzU2VlQWxzbyI6IFsiR01MIiwgIlhNTCJdCiAgICAgICAgICAgICAgICAgICAgfSwKCQkJCQkiR2xvc3NTZWUiOiAibWFya3VwIgogICAgICAgICAgICAgICAgfQogICAgICAgICAgICB9CiAgICAgICAgfQogICAgfQp9"),
				headersLeft);
		ResponseEntity<Object> responseBody = this.restTemplate.exchange(
				"http://localhost:" + port + "/v1/diff/Integration/left", HttpMethod.POST, requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		requestBody = new HttpEntity<>(new StoreJSONRequest(
				"eyJ3aWRnZXQiOiB7CiAgICAiZGVidWciOiAib24iLAogICAgIndpbmRvdyI6IHsKICAgICAgICAidGl0bGUiOiAiU2FtcGxlIEtvbmZhYnVsYXRvciBXaWRnZXQiLAogICAgICAgICJuYW1lIjogIm1haW5fd2luZG93IiwKICAgICAgICAid2lkdGgiOiA1MDAsCiAgICAgICAgImhlaWdodCI6IDUwMAogICAgfSwKICAgICJpbWFnZSI6IHsgCiAgICAgICAgInNyYyI6ICJJbWFnZXMvU3VuLnBuZyIsCiAgICAgICAgIm5hbWUiOiAic3VuMSIsCiAgICAgICAgImhPZmZzZXQiOiAyNTAsCiAgICAgICAgInZPZmZzZXQiOiAyNTAsCiAgICAgICAgImFsaWdubWVudCI6ICJjZW50ZXIiCiAgICB9LAogICAgInRleHQiOiB7CiAgICAgICAgImRhdGEiOiAiQ2xpY2sgSGVyZSIsCiAgICAgICAgInNpemUiOiAzNiwKICAgICAgICAic3R5bGUiOiAiYm9sZCIsCiAgICAgICAgIm5hbWUiOiAidGV4dDEiLAogICAgICAgICJoT2Zmc2V0IjogMjUwLAogICAgICAgICJ2T2Zmc2V0IjogMTAwLAogICAgICAgICJhbGlnbm1lbnQiOiAiY2VudGVyIiwKICAgICAgICAib25Nb3VzZVVwIjogInN1bjEub3BhY2l0eSA9IChzdW4xLm9wYWNpdHkgLyAxMDApICogOTA7IgogICAgfQp9fSAgIA=="),
				headersLeft);
		responseBody = this.restTemplate.exchange("http://localhost:" + port + "/v1/diff/Integration/right",
				HttpMethod.POST, requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		responseBody = this.restTemplate.exchange("http://localhost:" + port + "/v1/diff/Integration", HttpMethod.GET,
				requestBody, Object.class);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
	}
}
