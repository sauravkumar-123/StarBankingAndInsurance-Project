package com.Smartpay.Model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.Smartpay.Enum.EnumsStatus.AccountType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "MerchantBankDetails")
public class MerchantBankDetails extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "BankDetailsID", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String bankDetailsID;

	@NotBlank(message = "Invalid AccountHolder Name")
	@Column(name = "AccountholderName", length = 200)
	@Size(min = 1, max = 200, message = "Minimum 1 Or Maximum 200 Character are Allowed")
	private String accountHolderName;

	@NotBlank(message = "Invalid Account Number")
	@Column(name = "AccountNumber", length = 50)
	private String accountNumber;

	@NotBlank(message = "Invalid IFSC Code")
	@Column(name = "IFSCCode", length = 11)
	@Size(min = 11, max = 11, message = "IFSC Code should be 11 Character")
	private String ifscCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "AccountType", length = 15)
	private AccountType accountType;

	@NotBlank(message = "Invalid Bank Name")
	@Column(name = "BankName", length = 200)
	private String bankName;

	@NotBlank(message = "Invalid Branch Name")
	@Column(name = "BranchName", length = 200)
	private String branchName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "MerchantIdentificationNo")
	private Merchant merchant;
}