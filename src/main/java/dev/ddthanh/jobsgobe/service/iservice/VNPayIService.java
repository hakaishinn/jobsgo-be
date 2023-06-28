package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.payload.request.vnPay.VNPayRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.vnPay.UrlResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

public interface VNPayIService {
    public Response<UrlResponse> getUrlVnPay(VNPayRequest request) throws UnsupportedEncodingException;
    public void transaction(String vnp_responseCode, String vnpTxnRef,HttpServletResponse response);
}
