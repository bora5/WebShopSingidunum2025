package utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {

	private static String SECRET = "MojaJakoSigurnaŠifraZaJWT!!!";
	private static String ALGORITHM = "HmacSHA256";
	private static int ttl = 3600;

	public static String generateToken(String username, Long userId) {
		try {
			String header = """
					{
					"alg":"HS256",
					"typ":"JWT"
					}	
					""";
			String headerEncoded = base64EncodeURL(header);

			long exp = Instant.now().getEpochSecond() + ttl;
			String payload = """
					{
					"sub":"%s",
					"uid":%d,
					"exp":%d
					}	
					""".formatted(username, userId, exp);
			String payloadEncoded = base64EncodeURL(payload);
			
			String dataToSign = headerEncoded + "." + payloadEncoded;
			String signature = hmacSHA256(dataToSign);
			
			return dataToSign + "." + signature;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean validateToken(String token) {
		if (token == null)
			return false;
		
		String[] parts = token.split("\\.");
		if (parts.length != 3)
			return false;
		
		String headerEncoded = parts[0];
		String payloadEncoded = parts[1];
		String signature = parts[2];
		
		try {
			String dataToSign = headerEncoded + "." + payloadEncoded;
			String signatureCalculated = hmacSHA256(dataToSign);
			
			if (!signatureCalculated.equals(signature))
				return false;
			
			String payload = new String(Base64.getUrlDecoder().decode(payloadEncoded));
			long exp = extractExpiration(payload);
			
			if (Instant.now().getEpochSecond() > exp)
				return false;
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	private static String base64EncodeURL(String header) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
	}

	private static String hmacSHA256(String dataToSign) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance(ALGORITHM);
		SecretKeySpec secretKey = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);
		mac.init(secretKey);
		
		byte[] signedBytes = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));
		return Base64.getUrlEncoder().withoutPadding().encodeToString(signedBytes);
	}
	
	private static long extractExpiration(String payload) {
		String term = "\"exp\":";
		int start = payload.indexOf(term);
		if (start == -1) return 0;
		start += term.length();
		int end = payload.indexOf("}", start);
		if (end == -1) return 0;
		String number = payload.substring(start, end).trim();
		return Long.parseLong(number);
	}

}
