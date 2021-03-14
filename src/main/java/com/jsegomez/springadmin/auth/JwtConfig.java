package com.jsegomez.springadmin.auth;

public class JwtConfig {
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAs4HziamvTHPmDr77wsVPiuC68tTZP6C2kvgYIXJyIW6Mr1IV\r\n"
			+ "oHiPnVhNMtCagdITjQCRBHFfrCVB+4ZrCS6+CwXV80alQyp8l9mYnHXUzoCp9C1O\r\n"
			+ "H216K2glez8h18OMMKFUWLtptKYMVhANk8xTiInHv9whb1wZJTDAOa6F28gzyNjw\r\n"
			+ "fb0V7JrFs84zqq5bdtDe+QgHCZUymHww4mBCTL6uViOf/vYHtojZQ6wXRQ06XhJV\r\n"
			+ "n50ACHgyS/YtEf37mDWWeRkgxAl+X6HPfONpLD5xlmioxOTEZJQoUDfA87u1HbmJ\r\n"
			+ "ot7ny3fXVdEgAWhnJi8s/yqdmr0lrxoankYqFwIDAQABAoIBAGJKPLzbQdZd+9GF\r\n"
			+ "N7uNaKGFrjf63j4d1PqiiCwAS4gMzVHOY3MrsI+aYyL44DFHg1cIwV8qaRWMjRWZ\r\n"
			+ "o5AynPLSLjV4MFOXtwM6rXq2dmDgSK8gA4/n1SZhb2yNxA0t0Nxr6hBwBlBZP5X8\r\n"
			+ "UkLkdbTkpJBki5Y7WrVnfUnYSwltKjaWt69IhRvPfQ5cmSanwx+zLY23UGZ2TpY8\r\n"
			+ "OmL4St5wEDtAv9HYucs2+Gqp3gWVLLSe+z5HlMYKYcHtF9MEi9NHQP7WLm4HS+bq\r\n"
			+ "b7OxLwypXuzQPYUM1ZAIFAAZagi6TOuu9qk8l5VLarA1UAFBZd8vAKyH7tJ5TYn9\r\n"
			+ "t3mAyCECgYEA3RUi5hE2HmTUNs6SIeXD4IoeyIogtJV/t420uyXfF2DzedR6Y61z\r\n"
			+ "lFm4KgwQ9K5vYxewUAseC86qtWILjHL48CiQqA5/fnU5+MMAallMMe6WXGyzonGI\r\n"
			+ "qW5hoQPzAP705QpK3VH3um5qxn3jKOpJ87dmc/LvyKRau4GyrvIWbgcCgYEAz9vY\r\n"
			+ "zzSWvorcPwZ+z35TnTFLB416/jQlFQYCBIUa4DWsRIqOo9Hk/JOP+bgZiv1hBbmn\r\n"
			+ "mYVhZAftsRpoHVBWV/3CFBcCzqS/Xwthv8gMnsa3mhLDaVIYNSa14K1i8llZExPe\r\n"
			+ "SaN0aIaVqKCDbuLGT4fjoDQnZ1yG1yGd8RFdX3ECgYEApKRm9vr+WKgyOhagUUox\r\n"
			+ "E3kI78OeE536HRIU9AKCH4D+EU6hTrYPiv0932JZBj8ZiKxNi26cE6L3EllPzGqa\r\n"
			+ "5Q7wm5cgb2aRo6ZlGs4hplXN+EqjhGgUpEklv6mXQLieXCvMEQJIALjWH7gEgaXp\r\n"
			+ "KOz+CX9z/vIW9+jgztROy+UCgYAro21GOzU2tbJ9rOBssYneY53r7QEYY1wxqLw9\r\n"
			+ "tLAqyDntfeVqcUOQ324HsGX0bzOyXwLX0U/cD2oQl4mqNdaazJS1YQte1Om0i5Av\r\n"
			+ "ElHL985OE4hhETIx3nmqvNwcIzP3NGGjtiRXWYIMD/7oBcY6pj3cwXZQVfWdLtqY\r\n"
			+ "5NtkkQKBgECC6Eq0xONhqMOyAUFRiNBQjjplMXVgwnvwI7e009RvTvriWO/yf+5E\r\n"
			+ "cHY/EEVn3Ckhx6Ua+wqvJ//cBo2nOeu3s3pCM2qmDjZBACyW0CQKut10f+O+D8Vs\r\n"
			+ "PK5JD49yFSmcKA0hJh0Twu04cqHpHe7LjOufbGDqor2HOkk6bw8h\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs4HziamvTHPmDr77wsVP\r\n"
			+ "iuC68tTZP6C2kvgYIXJyIW6Mr1IVoHiPnVhNMtCagdITjQCRBHFfrCVB+4ZrCS6+\r\n"
			+ "CwXV80alQyp8l9mYnHXUzoCp9C1OH216K2glez8h18OMMKFUWLtptKYMVhANk8xT\r\n"
			+ "iInHv9whb1wZJTDAOa6F28gzyNjwfb0V7JrFs84zqq5bdtDe+QgHCZUymHww4mBC\r\n"
			+ "TL6uViOf/vYHtojZQ6wXRQ06XhJVn50ACHgyS/YtEf37mDWWeRkgxAl+X6HPfONp\r\n"
			+ "LD5xlmioxOTEZJQoUDfA87u1HbmJot7ny3fXVdEgAWhnJi8s/yqdmr0lrxoankYq\r\n"
			+ "FwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
}
