package com.example.retrofit.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义Converter
 * Created by Administrator on 2017/2/16.
 */

public class SelfConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    class SelfResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        @Override
        public T convert(ResponseBody value) throws IOException {
            return null;
        }
    }

    class SelfRequestBodyConverter<T> implements Converter<T,RequestBody>{

        @Override
        public RequestBody convert(T value) throws IOException {
            return null;
        }
    }
}

