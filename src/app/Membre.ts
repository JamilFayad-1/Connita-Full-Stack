export class Membre {
    idMembre: number;
    nom: string;
    prenom: string;
    email: string;
    password: string;
    region?: string;
    langue?: string;
    photoProfilPath: string;
    bio?: string;
    username?: string;
    privilege: number;
  
    constructor(
      idMembre: number,
      nom: string,
      prenom: string,
      email: string,
      password: string,
      privilege: number,
      photoProfilPath: string,
      region?: string,
      langue?: string,
      bio?: string,
      username?: string
    ) {
      this.idMembre = idMembre;
      this.nom = nom;
      this.prenom = prenom;
      this.email = email;
      this.password = password;
      this.region = region;
      this.langue = langue;
      this.photoProfilPath = photoProfilPath;
      this.bio = bio;
      this.username = username;
      this.privilege = privilege;
    }
}