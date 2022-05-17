package kz.pine.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Data
@ToString(of = {"id", "name", "image"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
}