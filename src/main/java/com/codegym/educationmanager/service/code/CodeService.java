package com.codegym.educationmanager.service.code;

import com.codegym.educationmanager.model.code.Code;
import com.codegym.educationmanager.repository.ICodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeService implements ICodeService {
    @Autowired
    private ICodeRepository codeRepository;

    @Override
    public Iterable<Code> findAll() {
        return codeRepository.findAll();
    }

    @Override
    public Optional<Code> findById(Long id) {
        return codeRepository.findById(id);
    }

    @Override
    public Code save(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public void deleteById(Long id) {
        codeRepository.deleteById(id);
    }
}
