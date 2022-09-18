package com.Smartpay.Model;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.Smartpay.Enum.EnumsStatus.BusinessType;
import com.Smartpay.Enum.EnumsStatus.DocumentsUploadStatus;
import com.Smartpay.Enum.EnumsStatus.Gender;
import com.Smartpay.Enum.EnumsStatus.MaritalStatus;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "Merchant")
public class Merchant extends BaseEntity {

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "uuid.hex")
	@Column(name = "MerchantIdentificationNo", length = 200)
	@Size(min = 1, max = 200, message = "min 1 and max 200 character are allowed")
	private String MerchantIdentificationNo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	@JoinColumn(name = "Username")
	private User user;

	@OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<MerchantBankDetails> bankDetails;

	@OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Address> addresses;

	@OneToOne(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private AEPSWallet aepsWallet;

	@OneToOne(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private MerchantDocuments merchantDocuments;

	@NotBlank(message = "Invalid Guardian Name")
	@Size(min = 1, max = 200, message = "Minimum 1 Or Maximum 200 Character are Allowed")
	@Column(name = "GuardianName", length = 200)
	private String guardianName;

	@Enumerated(EnumType.STRING)
	@Column(name = "MaritalStatus", length = 15)
	private MaritalStatus maritalStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "Gender", length = 15)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "BusinessType", length = 50)
	private BusinessType businessType;

	@Enumerated(EnumType.STRING)
	@Column(name = "AEPSServiceStatus", length = 4)
	private YesNO aepsServiceStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "EKYC_Status", length = 4)
	private YesNO EKYCstatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "BankOnboardStatus", length = 4)
	private YesNO bankOnboardStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "DocumentsUploadStatus", length = 25)
	private DocumentsUploadStatus documentsUploadStatus;

	@Column(name = "isActive")
	private char isActive;

	@Column(name = "PancardNumber", length = 10)
	@Size(min = 10, max = 10, message = "PanCard should be 10 Character")
	private String panCardNo;

	@Column(name = "AadhaarcardNumber", length = 12)
	@Size(min = 12, max = 12, message = "Aadhaar Number should be 12 Digit")
	private String aadhaarcardNo;

	@Column(name = "BusinessPanNo", length = 100)
	private String businesspanno;

	@NotBlank(message = "Invalid GST Nummber")
	@Column(name = "GSTNo", length = 100)
	private String gstNo;

	@NotBlank(message = "Invalid TAN Nummber")
	@Column(name = "TANNo", length = 100)
	private String tanNo;

}