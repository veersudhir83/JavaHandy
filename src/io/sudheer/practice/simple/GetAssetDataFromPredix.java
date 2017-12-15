package io.sudheer.practice.simple;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class GetAssetDataFromPredix {

	public static void main(String args[]) {
		HttpClient httpClient = HttpClientBuilder.create().build(); // Use this
																	// instead

		try {
			HttpGet request = new HttpGet("https://predix-asset.run.aws-usw02-pr.ice.predix.io/builds/1096");

			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImxlZ2FjeS10b2tlbi1rZXkiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiJmZDljMzNlNzFkNWM0OWI1YjYzOGZjMjRmOGRhMWE0YyIsInN1YiI6ImFkbWluIiwic2NvcGUiOlsiY2xpZW50cy5yZWFkIiwiY2xpZW50cy5zZWNyZXQiLCJpZHBzLndyaXRlIiwicHJlZGl4LWFzc2V0LnpvbmVzLjc1ZGYyMzQ1LTE5NDctNDY4MS05Yzg5LTM1NWE1MjYzZjAzOS51c2VyIiwidWFhLnJlc291cmNlIiwiY2xpZW50cy53cml0ZSIsImNsaWVudHMuYWRtaW4iLCJpZHBzLnJlYWQiLCJzY2ltLndyaXRlIiwic2NpbS5yZWFkIiwiem9uZXMuYTMyMmJiZjAtMjlmMC00NDFhLTk1ZjgtMzM0MzY1ZWNiODk3LmFkbWluIl0sImNsaWVudF9pZCI6ImFkbWluIiwiY2lkIjoiYWRtaW4iLCJhenAiOiJhZG1pbiIsImdyYW50X3R5cGUiOiJjbGllbnRfY3JlZGVudGlhbHMiLCJyZXZfc2lnIjoiYzlkYzIyNDEiLCJpYXQiOjE0NzQ1MzI4NTYsImV4cCI6MTQ3NDU3NjA1NiwiaXNzIjoiaHR0cHM6Ly9nZS10ZWNobS12ZWVyYXZzdS5wcmVkaXgtdWFhLnJ1bi5hd3MtdXN3MDItcHIuaWNlLnByZWRpeC5pby9vYXV0aC90b2tlbiIsInppZCI6ImEzMjJiYmYwLTI5ZjAtNDQxYS05NWY4LTMzNDM2NWVjYjg5NyIsImF1ZCI6WyJzY2ltIiwiY2xpZW50cyIsInVhYSIsImFkbWluIiwicHJlZGl4LWFzc2V0LnpvbmVzLjc1ZGYyMzQ1LTE5NDctNDY4MS05Yzg5LTM1NWE1MjYzZjAzOSIsImlkcHMiLCJ6b25lcy5hMzIyYmJmMC0yOWYwLTQ0MWEtOTVmOC0zMzQzNjVlY2I4OTciXX0.mKAUcV1_eVNYynxCi6jHWobADi58huATVHqLAgMncAGzq0Zpi8K61U8arPreACH5o-42nQhcqDLYYcOKF9-TfUJh20F4ErfDKvhaGs2vqn1Nz4m_A6B0Z4n_dcQ0H-fQwOFT0pwKMqs2vSuE0z4lzdZ3QTtajzu6Ci8q9Jf9O69CAkMzoHirRcdRQwM3da8h5yoIlkJdHQCloV9V_BCmnN1kmy4a7uf0c-9zDUf3pID3IMX8hQqI-bJmNABEMM0aDKFj3ccWIWsD0h4979LJCElHV3vZN5oES8kzG4d439icBpLMJ7JVjTWSY_Tqn4rMASUVmIbTj8CMh-xRHCIjyQ");
			request.addHeader("Predix-Zone-Id", "75df2345-1947-4681-9c89-355a5263f039");

			HttpResponse response = httpClient.execute(request);
			System.out.println(response.getStatusLine());
			String responseString = new BasicResponseHandler().handleResponse(response);
			/*HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");*/
			System.out.println(responseString);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}
}
