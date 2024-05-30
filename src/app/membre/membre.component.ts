import { Component, OnInit } from '@angular/core';
import { Membre } from '../Membre'
import { MembreService } from '../membre.service'
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-membre',
  templateUrl: './membre.component.html',
  styleUrl: './membre.component.css'
})
export class MembreComponent implements OnInit {
  membres?: Membre[];
  constructor(private membreService: MembreService, public dialog: MatDialog) {
  }
  ngOnInit() {
    this.membreService.getMembres().subscribe(
      (data) => {
        this.membres = data;
      },
      (error) => {
        console.error('Error fetching membres', error);
      }
    );
  }

  getPfp(path: string): string {
    return `http://localhost:8080/${path}`
  }

  openConfirmDialog(idMembre: Number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: { message: 'Are you sure you want to terminate this account?',
              title: 'Account Termination' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteMembre(idMembre);
      }
    });
  }

  deleteMembre(idMembre: Number) {
    this.membreService.deleteMembre(idMembre).subscribe(() => {
      const element = document.getElementById(`membre-${idMembre}`);
      if (element) {
        element.remove();
      }
      console.log(`Membre with id ${idMembre} deleted successfully`);
    });
  }

  searchForUser(username:string): void{
    this.membreService.getMembresByUsername(username).subscribe(
      (data) => {
        this.membres = data;
      },
      (error) => {
        console.error('Error fetching membres', error);
      }
    );
  }
}
