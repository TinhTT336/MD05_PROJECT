package com.example.md05_project.service.borrowedCartDetail;

import com.example.md05_project.exception.BookException;
import com.example.md05_project.exception.CustomException;
import com.example.md05_project.exception.UserNotFoundException;
import com.example.md05_project.model.dto.response.BorrowedCartDetailDTO;

import java.util.List;

public interface BorrowedCartDetailService {
    List<BorrowedCartDetailDTO> findByCartId(Long cartId) throws CustomException;
    BorrowedCartDetailDTO save(BorrowedCartDetailDTO borrowedCartDetailDTO) throws CustomException, UserNotFoundException, BookException;
    BorrowedCartDetailDTO findById(Long id) throws CustomException;
}
