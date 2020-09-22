package com.mugbya.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
}
