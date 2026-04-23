package com.example.dogservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@lombok.EqualsAndHashCode.Include
	private String id;
	private Double amount;
	private String channel;
	private String currency;
}
