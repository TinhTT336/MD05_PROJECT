package com.example.md05_project.service.WaitingRequestDetail;

import com.example.md05_project.exception.CustomException;
import com.example.md05_project.model.dto.response.WaitingRequestDetailDTO;
import com.example.md05_project.model.entity.WaitingRequestDetail;
import com.example.md05_project.repository.BookRepository;
import com.example.md05_project.repository.WaitingRequestDetailRepository;
import com.example.md05_project.repository.WaitingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingRequestDetailServiceImpl implements WaitingRequestDetailService {
    @Autowired
    private WaitingRequestDetailRepository waitingRequestDetailRepository;
    @Autowired
    private WaitingRequestRepository waitingRequestRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<WaitingRequestDetailDTO> findByRequestId(Long id) throws CustomException {
        List<WaitingRequestDetail>list = waitingRequestDetailRepository.findAllByRequestId(id);
        if(list.isEmpty()){
            throw new CustomException("Don't find waiting request with this id "+id);
        }
        return list.stream().map(WaitingRequestDetailDTO::new).toList();
    }

    @Override
    public WaitingRequestDetailDTO save(WaitingRequestDetailDTO waitingRequestDetailDTO) {
        WaitingRequestDetail waitingRequestDetail=waitingRequestDetailRepository.save(WaitingRequestDetail.builder()
//                        .id(waitingRequestDetailDTO.getId())
                        .waitingRequest(waitingRequestRepository.findById(waitingRequestDetailDTO.getWaitingRequestId()).orElse(null))
                        .book(bookRepository.findBookByTitle(waitingRequestDetailDTO.getBook()))
                .build());
        return new WaitingRequestDetailDTO(waitingRequestDetail);
    }

    @Override
    public WaitingRequestDetail findById(Long id) throws CustomException {
        return waitingRequestDetailRepository.findById(id).orElseThrow(()->
                new CustomException("Don't find waiting request with this id "+id));
    }
}
