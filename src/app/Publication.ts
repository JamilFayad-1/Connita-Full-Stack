import { Membre } from './Membre';
export class Publication {
    id_publication: number;
    titre: string;
    description: string;
    image: string;
    likes: number;
    membre: Membre;
  
    constructor(
      id_publication: number,
      titre: string,
      description: string,
      image: string,
      likes: number,
      membre: Membre
    ) {
      this.id_publication = id_publication;
      this.titre = titre;
      this.description = description;
      this.image = image;
      this.likes = likes;
      this.membre = membre;
    }
  }

