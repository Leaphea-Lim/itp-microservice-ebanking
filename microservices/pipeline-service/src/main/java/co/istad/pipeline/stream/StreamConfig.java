//package co.istad.pipeline.stream;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.StringReader;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import org.apache.avro.generic.GenericRecord;
//import org.w3c.dom.Document;
//import org.xml.sax.InputSource;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//@Configuration
//public class StreamConfig {
//
//    // Supplier for producing message into kafka topic
//    // Function for processing message and send to destination kafka topic
//    // Consumer for consuming message from kafka topic
//
//    @Bean
//    public Function<Product, Product> processProductDetail() {
//        return product -> {
//            System.out.println("old product: " + product.getCode());
//            System.out.println("old product: " + product.getQty());
//
//            //processing
//            product.setCode("ISTAD- " + product.getCode());
//
//            return product;
//        };
//    }
//
//    @Bean
//    public Consumer<Product> processProduct() {
//        return product -> {
//            System.out.println("obj product: " + product.getCode());
//            System.out.println("obj product: " + product.getQty());
//        };
//    }
//
//    // A simple processor: Takes a string, makes it uppercase, and sends it on
//    @Bean
//    public Consumer<String> processMessage() {
//        return input -> {
//            System.out.println("Processing: " + input);
//        };
//    }
//
//    @Bean
//        public Function<GenericRecord, Product> processProductDebezium(){
//        return record -> {
//            GenericRecord after = (GenericRecord) record.get("after");
//            if (after != null) {
//                // Safely read fields
//                Object codeObj = after.get("code");
//                Object qtyObj = after.get("qty");
//
//                String code = codeObj != null ? codeObj.toString() : "N/A";
//                Integer qty = qtyObj != null ? (Integer) qtyObj : 0;
//
//                System.out.println("Debezium -> Product Code: " + code);
//                System.out.println("Debezium -> Product Qty: " + qty);
//
//                Product product = new Product();
//                product.setCode(code);
//                product.setQty(qty);
//
//                return product;
//            }
//            return null;
//        };
//    }
//
//    //recordXml
////    @Bean
////    public Function<GenericRecord, RecordXml> processDebeziumRecordXml() {
////        return record -> {
////            GenericRecord after = (GenericRecord) record.get("after");
////            try {
////                DebeziumEnvelope<Record> capturedRecord =
////                        objectMapper.readValue(record.getPayload().toString(),
////                                new TypeReference<>(){});
////                return switch (capturedRecord.getOp()) {
////                    case "r", "c" -> {
////                        System.out.println("Prepare to insert new record");
////                        Record after = capturedRecord.getAfter();
////                        System.out.println(after.getXmldata().getName());
////                        yield after;
////                    }
////                    case "u" -> {
////                        System.out.println("Prepare to update existing record");
////                        Record after = capturedRecord.getAfter();
////                        System.out.println("Updated: " + after.getXmldata().getName());
////                        yield after;
////                    }
////                    case "d" -> {
////                        System.out.println("Prepare to delete existing record");
////                        System.out.println("Delete ID = " + capturedRecord.getBefore().getRecid());
////                        yield capturedRecord.getBefore();
////                    }
////                    default -> throw new IllegalStateException("Invalid Operation..!");
////                };
////            } catch (JsonProcessingException e) {
////                System.out.println("Error deserialized");
////                throw new RuntimeException("Error deserialized");
////            }
////        };
////    }
//
//}
