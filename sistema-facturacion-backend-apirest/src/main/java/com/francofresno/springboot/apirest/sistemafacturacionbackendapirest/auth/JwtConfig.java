package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAzFs5cfmlZvGDsFuzLCIKStJOVyVoryMV8ISKZegoISIFE8ra\r\n" + 
			"rxM6iDD4N/8cYXVS4vjwrA037Fc/llaRlwhUvBjZig66hkjlpSee04AkbDeFFxXt\r\n" + 
			"haM5H6oKDFT51z/L4urKtRNiB/KpLf1r578NYvXwOU4x5+pAqReUAqhxQagM1mJ+\r\n" + 
			"JhdSId8x5JxPwGtTcGZ6mgqvEMrkMdinLHHo61m5mQouikTg0J71FDAigPhVFNcR\r\n" + 
			"MDBjSDdbG4WvE1ft9cGIn5NcdE82/TIvYechNroKPKyULpjcds4TS1ijV2O8k2Em\r\n" + 
			"/V4rdR4UH7d+R6PuC+VJTu5iMrAwGJJPlZOQLwIDAQABAoIBAB5LC5n0qus2tBHJ\r\n" + 
			"libdqO7sfAZ5GiqUJeVwkF+I6aH3vA7yDDbVv+B+ed07KQbdcHsK8gpfL0IOsPNl\r\n" + 
			"Q9HCv2Tevii0BDxxw/EWjXnGzh9on4cLjhk4V/h0qWaolhytbVjm+0OaavbIpwGV\r\n" + 
			"RpClBqP71y93gvhhBlLKlvtJ4vgbYCN5Fa5UWQEpn2+JHX2HBr73En3fCO4Oed7x\r\n" + 
			"+Eeel2sU952vPvUPWGXNcpTTlbyJbLr5ln6SGYXA+PvBDfSdPy9luRNup91Fi3B/\r\n" + 
			"t0uN9qCUT0fsl1bVCWz+ea8+8ELeQFHfhpm89II1pY5bqCv8tkIsu1/ogbkzOR83\r\n" + 
			"2elI2TECgYEA+9Qf3LQD0wOEaS4KVJsrUKFqvIMRLoWpx+HkIbWxagwLSbYuHbaN\r\n" + 
			"q3ZFtLRC1Ecd/48P+h72XtC4L4Ota4Bs8KwqYC7uaCUZJ4vast/Y1jlpx/HiItxs\r\n" + 
			"zAs8CC7ibz+y+zdSd/hgOHyXWuAo10rloY96twudFt4rAkECYKLgh1kCgYEAz73L\r\n" + 
			"Y2eI9eU8R7iINEHuw3y/Jkh5akZDcqdJXZsKaL79amoR/UQ5+fQwm6x45fpr+BJF\r\n" + 
			"2jf5KkjKLBMdIHTs1tnJG2Rl8Pb6LA8MZDSM+9fYE2GWeLbm8EwppjG16CdvUiD0\r\n" + 
			"xKWPc+2jowjElC6aPn8hdYzCzDh78p6lEECL6scCgYB5LqW6fgHNHEcumhIsjpL0\r\n" + 
			"dQi4+FVfTsDZx/EQ668ZOfyAlyNv9vYLiZmXkJsMLcDYxs5IbXffPo8zhikAr0c9\r\n" + 
			"NYwaxtrA0QFez2UJaMCerfgiWFqarfnI4qsbwPwDzuJhE0kjJCmhZQssluvwVkFU\r\n" + 
			"UogZ54PLilOl/QSY3igv2QKBgDvo7ELalPD1DxFP8le/u++8hhOwSvOPF9x0yf+o\r\n" + 
			"urPCgKqztERp7J5dAbejYapRMdn0yxhHlB/RphxX1sqJbdP4MrsnlQ6M61DRdi1f\r\n" + 
			"yljGwpW0SwS9GpSqIki/hUpSNRRmVLBxXpF8MvjNioOBfRIQ78FKutLSZ0xs3AV3\r\n" + 
			"1+H5AoGBAL37SiS6nKsCu0YikV6Tk4NLOhHfZokHtzv/UInsMsUYHlmAE5RWQjzU\r\n" + 
			"ovxay8MrfdR9/+XK0VA8UxI5s47qu0GrGjnzGxNPaSf17GFRcdYX0bLgzy00QYJ4\r\n" + 
			"Df1ZsdP04IUsP36GbhOMNuBQE2tjoY6P6dHdyKNuhFLIhXCXxLIq\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzFs5cfmlZvGDsFuzLCIK\r\n" + 
			"StJOVyVoryMV8ISKZegoISIFE8rarxM6iDD4N/8cYXVS4vjwrA037Fc/llaRlwhU\r\n" + 
			"vBjZig66hkjlpSee04AkbDeFFxXthaM5H6oKDFT51z/L4urKtRNiB/KpLf1r578N\r\n" + 
			"YvXwOU4x5+pAqReUAqhxQagM1mJ+JhdSId8x5JxPwGtTcGZ6mgqvEMrkMdinLHHo\r\n" + 
			"61m5mQouikTg0J71FDAigPhVFNcRMDBjSDdbG4WvE1ft9cGIn5NcdE82/TIvYech\r\n" + 
			"NroKPKyULpjcds4TS1ijV2O8k2Em/V4rdR4UH7d+R6PuC+VJTu5iMrAwGJJPlZOQ\r\n" + 
			"LwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
