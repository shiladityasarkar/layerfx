package com.shiladitya.layerfx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Lob
	@Column(name="imageconv", columnDefinition="LONGBLOB")
	private byte[] image_conv;
//	@Column(name="imagedata2", length=2000)
	@Column(name="imagedata2", columnDefinition="LONGBLOB")
	private byte[] image_data2;
	@Column(name="imagedata1", columnDefinition="LONGBLOB")
	private byte[] image_data1;
}
