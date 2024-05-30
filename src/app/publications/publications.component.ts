import { Component, OnInit } from '@angular/core';
import { PublicationService } from '../publications.service';
import { Publication } from '../Publication';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-publications',
  templateUrl: './publications.component.html',
  styleUrls: ['./publications.component.css']
})
export class PublicationsComponent implements OnInit {
  publications?: Publication[];

  constructor(private publicationService: PublicationService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadPublications();
  }

  loadPublications(): void {
    this.publicationService.getPublications().subscribe(
      (data) => {
        this.publications = data;
      },
      (error) => {
        console.error('Error fetching publications', error);
      }
    );
  }

  getImageUrl(image: string): string {
    return `http://localhost:8080/imageUtilisateur/${image}`;
  }

  getPfp(path: string): string {
    return `http://localhost:8080/${path}`;
  }

  openConfirmDialog(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { message: 'Are you sure you want to delete this post?',
              title: 'Post Deletion' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deletePost(id);
      }
    });
  }

  deletePost(id: number): void {
        this.publicationService.deletePost(id).subscribe(() => {
          this.removePublicationFromList(id);
          console.log(`Post with id ${id} deleted successfully`);
        });
  }

  removePublicationFromList(id: number): void {
    if (this.publications) {
      this.publications = this.publications.filter(pub => pub.id_publication !== id);
    }
  }
}
