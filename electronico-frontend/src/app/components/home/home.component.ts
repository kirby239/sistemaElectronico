import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  examTitle: string = 'Full Stack Developer';
  examDescription: string = 'Bienvenido al Examen Técnico de Desarrollador Full Stack. Esta evaluación evaluará sus habilidades tanto en el desarrollo front-end como en el back-end.';
  descripcion :string ='Descripción del Proyecto'
}
