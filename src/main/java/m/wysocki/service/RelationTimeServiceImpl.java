package m.wysocki.service;

import m.wysocki.dao.CityRepository;
import m.wysocki.dao.RelationRepository;
import m.wysocki.dao.RelationTimeRepository;
import m.wysocki.dao.TicketRepository;
import m.wysocki.domain.City;
import m.wysocki.domain.Relation;
import m.wysocki.domain.RelationTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelationTimeServiceImpl implements RelationTimeService{

    RelationTimeRepository relationTimeRepository;
    RelationRepository relationRepository;
    CityRepository cityRepository;
    TicketRepository ticketRepository;

    @Autowired
    public RelationTimeServiceImpl(RelationTimeRepository relationTimeRepository, RelationRepository relationRepository,
                                   CityRepository cityRepository, TicketRepository ticketRepository) {
        this.relationTimeRepository = relationTimeRepository;
        this.relationRepository = relationRepository;
        this.cityRepository = cityRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public List<RelationTime> getListOfRelationTimes(){
       return relationTimeRepository.findAll();
    }

    @Override
    public RelationTime getRelationTime(long relationId) {
        return relationTimeRepository.findById(relationId);
    }

    @Override
    public List<Relation> listRelation(){
        return relationRepository.findAll();
    }

    @Override
    @Transactional
    public void addRelationTime(RelationTime relationTime){
        System.out.println(relationTime.getRelation().getCityFrom().getName());
        City cityFrom = cityRepository.findByName(relationTime.getRelation().getCityFrom().getName());
        if(cityFrom == null){
            cityFrom = new City();
        }
        cityFrom.setName(relationTime.getRelation().getCityFrom().getName());
        cityFrom = cityRepository.save(cityFrom);

        City cityTo = cityRepository.findByName(relationTime.getRelation().getCityTo().getName());
        if(cityTo == null){
            cityTo = new City();
        }
        cityTo.setName(relationTime.getRelation().getCityTo().getName());
        cityTo = cityRepository.save(cityTo);

        RelationTime newRelationTime = new RelationTime();

        newRelationTime.setTimeFrom(relationTime.getTimeFrom());
        newRelationTime.setTimeTo(relationTime.getTimeTo());

        Relation relation = relationRepository.findByCityToAndCityFrom(cityTo, cityFrom);
        if(relation == null){
            relation = new Relation();
        }

        relation.setCityFrom(cityFrom);
        relation.setCityTo(cityTo);
        relation = relationRepository.save(relation);

        newRelationTime.setRelation(relation);
        relationTimeRepository.save(newRelationTime);
    }

    @Override
    public void editRelationTime(RelationTime relationTime1) {
        City from = relationTime1.getRelation().getCityFrom();
        City to = relationTime1.getRelation().getCityTo();
        cityRepository.save(from);
        cityRepository.save(to);
        relationTimeRepository.save(relationTime1);
    }

    @Override
    public void deleteById(long relationId) {
        relationTimeRepository.delete(relationId);
    }

    @Override
    @Transactional
    public void removeTicket(long id) {
        System.out.println("removeTicket: " + id);
        ticketRepository.delete(id);
    }
}
