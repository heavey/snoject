package se.kth.inda14.snoject.engine;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JSONUtil
{
	public static String toJson(Object o)
	{
		return new Gson().toJson(o);
	}

	public static ResponseTransformer json()
	{
		return JSONUtil::toJson;
	}
}
