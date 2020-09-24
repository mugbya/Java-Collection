package com.mugbya.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user", type = "_doc")
public class User {
    @Id
    String id;

    @Field(type = FieldType.Keyword)
    String username;

    @Field(type = FieldType.Integer)
    Integer age;

    @Field(type = FieldType.Keyword)
    String gender;

    @Field(type = FieldType.Text)
    String desc;

    @Field(type = FieldType.Byte)
    Byte statue;

    @Field(type = FieldType.Keyword, index = false)//不会对图片地址查询,指定为false
    String images;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis", timezone="GMT+0")
    Date createTime;

}
