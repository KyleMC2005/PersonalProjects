package com.codewithkyle;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AiService aiService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AiService aiService, HandlerExceptionResolver handlerExceptionResolver) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
       String prompt = """
               Based on the programming tech stack %s that %s has given
               Provide a full learning path for this person.""".formatted(softwareEngineer.getTechStack(), softwareEngineer.getName());
        String chatRes = aiService.chat(prompt);
        softwareEngineer.setLearningPathRecommendation(chatRes);
        softwareEngineerRepository.save(softwareEngineer);
    }

    public void deleteSoftwareEngineer(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        softwareEngineerRepository.deleteById(id);
    }

    public void updateSoftwareEngineer(Integer id,SoftwareEngineer update) {
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        softwareEngineer.setName(update.getName());
        softwareEngineer.setTechStack(update.getTechStack());
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer  getAllSoftwareEngineersById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
    }
}
