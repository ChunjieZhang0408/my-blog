package com.example.vo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class Result extends HashMap<Object, Object>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6294970276876590953L;
	
	private static final String STATE = "state";
	private static final String STATE_OK = "success";
	private static final String STATE_FAIL = "failed";
	
	public Result() {
	}
	
	public static Result by(Object key, Object value) {
		return new Result().set(key, value);
	}
	
	public static Result create(Object key, Object value) {
		return new Result().set(key, value);
	}
	
	public static Result create() {
		return new Result();
	}
	
	public static Result ok() {
		return new Result().setOk();
	}
	
	public static Result ok(Object key, Object value) {
		return ok().set(key, value);
	}
	
	public static Result fail() {
		return new Result().setFail();
	}
	
	public static Result fail(Object key, Object value) {
		return fail().set(key, value);
	}
	
	public Result setOk() {
		super.put(STATE, STATE_OK);
		return this;
	}
	
	public Result setFail() {
		super.put(STATE, STATE_FAIL);
		return this;
	}
	
	public boolean isOk() {
		Object state = get(STATE);
		if (STATE_OK.equals(state)) {
			return true;
		}
		if (STATE_FAIL.equals(state)) {
			return false;
		}
		
		throw new IllegalStateException("调用 isOk() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
	}
	
	public boolean isFail() {
		Object state = get(STATE);
		if (STATE_FAIL.equals(state)) {
			return true;
		}
		if (STATE_OK.equals(state)) {
			return false;
		}
		
		throw new IllegalStateException("调用 isFail() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
	}
	
	public Result set(Object key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public Result setIfNotBlank(Object key, String value) {
		if (StringUtils.isNotBlank(value)) {
			set(key, value);
		}
		return this;
	}
	
	public Result setIfNotNull(Object key, Object value) {
		if (value != null) {
			set(key, value);
		}
		return this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Result set(Map map) {
		super.putAll(map);
		return this;
	}
	
	public Result set(Result Result) {
		super.putAll(Result);
		return this;
	}
	
	public Result delete(Object key) {
		super.remove(key);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAs(Object key) {
		return (T)get(key);
	}
	
	public String getStr(Object key) {
		Object s = get(key);
		return s != null ? s.toString() : null;
	}
	
	public Integer getInt(Object key) {
		Number n = (Number)get(key);
		return n != null ? n.intValue() : null;
	}
	
	public Long getLong(Object key) {
		Number n = (Number)get(key);
		return n != null ? n.longValue() : null;
	}
	
	public Number getNumber(Object key) {
		return (Number)get(key);
	}
	
	public Boolean getBoolean(Object key) {
		return (Boolean)get(key);
	}
	
	/**
	 * key 存在，并且 value 不为 null
	 */
	public boolean notNull(Object key) {
		return get(key) != null;
	}
	
	/**
	 * key 不存在，或者 key 存在但 value 为null
	 */
	public boolean isNull(Object key) {
		return get(key) == null;
	}
	
	/**
	 * key 存在，并且 value 为 true，则返回 true
	 */
	public boolean isTrue(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == true));
	}
	
	/**
	 * key 存在，并且 value 为 false，则返回 true
	 */
	public boolean isFalse(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == false));
	}
	
	public String toJson() {
		return JSONObject.toJSONString(this);
	}
	
	public boolean equals(Object Result) {
		return Result instanceof Result && super.equals(Result);
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	
}
