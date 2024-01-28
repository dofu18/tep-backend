package com.main.timeshareexchangeplatform_backend.vnpay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	private String code;
	private String message;
	private String data;
}
