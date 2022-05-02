package security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KorisnikRepository;

import model.Korisnik;

@Service 
public class UserDetailProvider implements UserDetailsService {
	
	@Autowired
	KorisnikRepository korisnikRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Korisnik k = korisnikRepo.findByUsername(username);
//		UserDetails ud = new MyUserDetails(k);
//		return ud;
		Optional<Korisnik> korisnik = Optional.of(korisnikRepo.findByUsername(username));
		korisnik.orElseThrow(() -> new UsernameNotFoundException("Nije pronadjeno korisnicko ime: "+ username));
		return korisnik.map(MyUserDetails::new).get();
	}

}
