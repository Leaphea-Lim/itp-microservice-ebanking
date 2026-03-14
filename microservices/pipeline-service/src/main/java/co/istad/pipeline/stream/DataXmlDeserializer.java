package co.istad.pipeline.stream;

public class DataXmlDeserializer extends XmlStringDeserializer<Data> {
    public DataXmlDeserializer() {
        super(Data.class);
    }
}