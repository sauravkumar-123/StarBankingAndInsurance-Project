package com.Smartpay.FileUpload.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Smartpay.DAO.MerchantRepository;
import com.Smartpay.Enum.EnumsStatus.YesNO;
import com.Smartpay.Exception.FileStorageException;
import com.Smartpay.Exception.ResourceNotFoundException;
import com.Smartpay.FileUpload.FileStorageService;
import com.Smartpay.FileUpload.MerchantDocuments;
import com.Smartpay.FileUpload.Dao.MerchantDocumentsRepository;
import com.Smartpay.FileUpload.Service.DocumentsUploadService;
import com.Smartpay.Model.Merchant;
import com.Smartpay.Request.MerchantDocumentsRequest;

import net.bytebuddy.asm.Advice.Return;

@Service
public class DocumentsUploadServiceImpl implements DocumentsUploadService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentsUploadServiceImpl.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantDocumentsRepository merchantDocumentsRepository;

	@Override
	public MerchantDocuments uploadDocumentsForBankingService(String username,
			MerchantDocumentsRequest merchantDocumentsRequest) {
		logger.info("Enter Into uploadDocumentsForBankingService");
		Merchant merchant = merchantRepository.findMerchantByUsername(username);
		if (null != merchant) {
			MerchantDocuments docs = merchantDocumentsRepository.getDocDetailsByMerchantDetail(merchant);
			if (null == docs) {
				String[] filelocationArr = fileStorageService.storeFile(merchantDocumentsRequest.getDocuments(),
						username);
				MerchantDocuments merchantDocuments = new MerchantDocuments();
				merchantDocuments.setMerchant(merchant);
				merchantDocuments.setAadhaarCardImagePath(filelocationArr[1]);
				merchantDocuments.setPanCardImagePath(filelocationArr[2]);
				merchantDocuments.setCancledCheckPath(filelocationArr[3]);
				merchantDocuments.setIsApproved(YesNO.NO);
				return merchantDocumentsRepository.saveDocumentsDetail(docs);
			} else {
				logger.info("Documents Already Uploaded");
				throw new FileStorageException("Documents Alreday Uploaded For : " + username);
			}
		} else {
			logger.info("Merchant Details Not Found To Uplaod Documents");
			throw new ResourceNotFoundException("Merchant Details Not Found");
		}

	}

}