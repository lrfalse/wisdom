package com.wisdom.third.wx.mp.bean.result;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class WxMpPromotionTransfersResult implements Serializable{
	@XStreamAlias("return_code")
	private String returnCode;
	@XStreamAlias("return_msg")
	private String returnMsg;
	@XStreamAlias("mch_appid")
	private String mchAppid;
	@XStreamAlias("mchid")
	private String mchid;
	@XStreamAlias("device_info")
	private String deviceInfo;
	@XStreamAlias("nonce_str")
	private String nonceStr;
	@XStreamAlias("result_code")
	private String resultCode;
	@XStreamAlias("err_code")
	private String errCode;
	@XStreamAlias("err_code_des")
	private String errCodeDes;
	@XStreamAlias("partner_trade_no")
	private String partnerTradeNo;
	@XStreamAlias("payment_no")
	private String paymentNo;
	@XStreamAlias("payment_time")
	private String paymentTime;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getMchAppid() {
		return mchAppid;
	}
	public void setMchAppid(String mchAppid) {
		this.mchAppid = mchAppid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	
}
