package facades;

import dtos.CharityDTO;
import dtos.NonProfitDTO;
import entities.Blacklist;
import entities.Role;
import entities.User;
import javassist.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BlacklistFacade {
    private static EntityManagerFactory emf;
    private static BlacklistFacade instance;
    BlacklistFacade(){
    }
    public static BlacklistFacade getBlacklistFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BlacklistFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<String> getBlacklist() throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Blacklist> query = em.createQuery("SELECT b FROM Blacklist b", Blacklist.class);
            if (query == null) {
                throw new NotFoundException("Can't find any users");
            }
            List<Blacklist> blacklists = query.getResultList();
            List<String> bannedSlugs = new ArrayList<>();
            for (Blacklist b: blacklists) {
                bannedSlugs.add(b.getBlacklistedSlug());
            }
            return bannedSlugs;
        } finally {
            em.close();
        }
    }
    public Blacklist blacklistCharity (Blacklist blacklist) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(blacklist);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return blacklist;
    }
    public NonProfitDTO removeBlacklistedItems(NonProfitDTO nonProfitDTO) throws NotFoundException {
        List<String> blacklists = getBlacklist();
        List<CharityDTO> removeThese = new ArrayList<>();
        for (CharityDTO c: nonProfitDTO.getNonprofits()) {
            for (int i = 0; i < blacklists.size(); i++) {
                if (c.getSlug().equals(blacklists.get(i))){
                    removeThese.add(c);
                }
            }
        }
        for (int i = 0; i < removeThese.size(); i++) {
            int finalI = i;
            nonProfitDTO.getNonprofits().removeIf(c ->(c.equals(removeThese.get(finalI))));
        }
        return nonProfitDTO;
    }

}
