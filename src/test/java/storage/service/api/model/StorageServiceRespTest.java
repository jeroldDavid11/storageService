package storage.service.api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StorageServiceRespTest {

	@Test
	void test() {
		StorageServiceResp storageServiceResp = new StorageServiceResp();
		storageServiceResp.setCreateDate("Test");
		storageServiceResp.setFilename("Test");
		storageServiceResp.setMessage("Test");
		storageServiceResp.setSize(0);
		storageServiceResp.setStatus(0);
		assertNotNull(storageServiceResp.getFilename());
		assertNotNull(storageServiceResp.getMessage());
		assertNotNull(storageServiceResp.getSize());
		assertNotNull(storageServiceResp.getStatus());;
		assertNotNull(storageServiceResp.getCreateDate());
	}

}
