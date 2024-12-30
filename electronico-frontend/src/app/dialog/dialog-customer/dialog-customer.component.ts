import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../../service/customer.service';
import { MensajeService } from '../../service/mensaje.service';
import { customer } from '../../interface/customer';
import { DialogModule } from '../../modules/dialog.module';

@Component({
  selector: 'app-dialog-customer',
  imports: [DialogModule],
  templateUrl: './dialog-customer.component.html',
  styleUrl: './dialog-customer.component.css'
})
export class DialogCustomerComponent {
  constructor(private dialogRef: MatDialogRef<DialogCustomerComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,
    private fb: FormBuilder,
    private CustomerService: CustomerService,
    private mensaje: MensajeService) { }
  datosPrincipales: customer;

  customerForm: FormGroup;
  modo: string;
  parametro_busqueda: string;

  ngOnInit(): void {
    this.modo = this.data['Modo']
    this.datosPrincipales = this.data['tercero'];
    this.parametro_busqueda = this.data['parametro_busqueda'];
    this.form()
  }
  form() {
    this.customerForm = this.fb.group({
      name: [this.datosPrincipales?.name, Validators.required],
      email: [this.datosPrincipales?.email, [Validators.required, Validators.email]],
    });

    if (this.modo == 'view') {
      this.customerForm.disable()
    }
  }
  cerrar() {
    this.dialogRef.close(this.parametro_busqueda);
  }
  save() {
    if (this.customerForm.valid) {
      const { id, name, email } = this.customerForm.value
      this.CustomerService.createCustomer({ id, name, email }).subscribe(save => {
        this.mensaje.MostrarMensaje('Creado Correctamente')
        this.cerrar();
      },error => {
        this.mensaje.MostrarMensaje('Ha Ocurrido un error')
        this.cerrar();
      })
    }
  }
}
