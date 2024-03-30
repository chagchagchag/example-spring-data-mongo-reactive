package io.chagchagchag.example_mongo.mongodb_reactive_example.examples.codec.valueobject;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter @Setter
public class Person {
  @BsonId
  @BsonProperty(value = "_id")
  private ObjectId id;
  @BsonProperty(value = "name")
  private String name;
  @BsonProperty(value = "salary")
  private BigDecimal salary;

  @Override
  public int hashCode() {
    return Objects.hash(id, name, salary);
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null || getClass() != obj.getClass()) return false;
    Person p = (Person) obj;
    return Objects.equals(id, p.getId())
        && Objects.equals(salary, p.getSalary())
        && Objects.equals(name, p.getName());
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", salary=" + salary +
        '}';
  }
}
