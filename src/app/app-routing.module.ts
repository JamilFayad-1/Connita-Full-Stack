import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicationsComponent } from './publications/publications.component';
import { MembreComponent } from './membre/membre.component';


const routes: Routes = [
  { path: 'publications', component: PublicationsComponent },
  { path: 'membres', component: MembreComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
