package com.cap.pricecontrolcenter.uils;

public abstract class TestInputHelper {
    public static class RequestPriceController {
        public static final Integer PRODUCT_ID = 35455;
        public static final Integer BRAND_ID = 1;


        public final static String BRAND_ID_NULL = "{\"brandId\":null,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}";

        public final static String START_DATE_NULL = "{\"brandId\":1,\"startDate\":null,\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}";
        public final static String END_DATE_NULL = "{\"brandId\":1,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":null,\"priceList\":1,\"productId\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}";

        public final static String PRIORITY_NULL = "{\"brandId\":1,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":null,\"price\":10.5,\"currency\":\"USD\"}";
        public final static String PRIORITY_MIN = "{\"brandId\":1,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":-1,\"price\":10.5,\"currency\":\"USD\"}";
        public final static String PRIORITY_MAX = "{\"brandId\":1,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":50,\"price\":10.5,\"currency\":\"USD\"}";
    }
}
