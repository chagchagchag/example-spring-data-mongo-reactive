package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.codec;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class CustomValueCodecProvider extends ValueCodecProvider {
  @Override
  public <T> Codec<T> get(Class<T> clazz, List<Type> typeArguments, CodecRegistry registry) {
    if(clazz == BigDecimal.class){
      return new Codec<T>(){
        @Override
        public void encode(BsonWriter bsonWriter, T value, EncoderContext encoderContext) {
          bsonWriter.writeString(((BigDecimal) value).toString());
        }

        @Override
        public Class<T> getEncoderClass() {
          return (Class<T>) BigDecimal.class;
        }

        @Override
        public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
          return (T) new BigDecimal(bsonReader.readString());
        }
      };
    }
    return null;
  }
}
