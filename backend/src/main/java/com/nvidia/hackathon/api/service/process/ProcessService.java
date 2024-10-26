package com.nvidia.hackathon.api.service.process;

import com.nvidia.hackathon.model.process.ProcessRequest;

public interface ProcessService {

	public String process(ProcessRequest processRequest) throws Exception;

}
