/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.people.ResearchTeam;
import fr.pfgen.lims.domain.people.ResearchUnit;
import fr.pfgen.lims.repository.ResearchTeamRepository;
import fr.pfgen.lims.repository.ResearchUnitRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eric
 */
@Service
@Transactional
public class ResearchTeamServiceImpl implements ResearchTeamService {

    @Autowired
    ResearchTeamRepository researchTeamRepository;
    @Autowired
    ResearchUnitRepository researchUnitRepository;

    //methode impl for Research Teams
    @Override
    public long countAllResearchTeams() {
        return researchTeamRepository.count();
    }

    @Override
    public void deleteResearchTeam(ResearchTeam researchTeam) {
        researchTeamRepository.delete(researchTeam);
    }

    @Override
    public ResearchTeam findResearchTeam(Long id) {
        return researchTeamRepository.findOne(id);
    }

    @Override
    public List<ResearchTeam> findAllResearchTeams() {
        return researchTeamRepository.findAll();
    }

    @Override
    public List<ResearchTeam> findResearchTeamEntries(int firstResult, int maxResults) {
        return researchTeamRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveResearchTeam(ResearchTeam researchTeam) {
        researchTeamRepository.save(researchTeam);
    }

    @Override
    public ResearchTeam updateResearchTeam(ResearchTeam researchTeam) {
        return researchTeamRepository.save(researchTeam);
    }

    @Override
    public List<ResearchTeam> findByResearchUnit(ResearchUnit researchUnit) {
        return researchTeamRepository.findByResearchUnit(researchUnit);
    }

    @Override
    public Map<ResearchUnit, List<ResearchTeam>> getUnits2Teams() {
        Map<ResearchUnit, List<ResearchTeam>> map = new HashMap<>();

        List<ResearchUnit> unitList = researchUnitRepository.findAll();
        for (ResearchUnit researchUnit : unitList) {
            List<ResearchTeam> teamList = researchTeamRepository.findByResearchUnit(researchUnit);
            map.put(researchUnit, teamList);
        }

        return map;
    }

    //methode impl for Research Teams
    @Override
    public List<ResearchUnit> findAllResearchUnits() {
        return researchUnitRepository.findAll();
    }

    @Override
    public long countAllResearchUnits() {
        return researchUnitRepository.count();
    }

    @Override
    public void deleteResearchUnit(ResearchUnit researchUnit) {
        researchUnitRepository.delete(researchUnit);
    }

    @Override
    public ResearchUnit findResearchUnit(Long id) {
        return researchUnitRepository.findOne(id);
    }

    @Override
    public List<ResearchUnit> findResearchUnitEntries(int firstResult, int maxResults) {
        return researchUnitRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveResearchUnit(ResearchUnit researchUnit) {
        researchUnitRepository.save(researchUnit);
    }

    @Override
    public ResearchUnit updateResearchUnit(ResearchUnit researchUnit) {
        return researchUnitRepository.save(researchUnit);
    }

    @Override
    public ResearchUnit findResearchUnitByName(String name) {
        return researchUnitRepository.findByName(name);
    }
}
