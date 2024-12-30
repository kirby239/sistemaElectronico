import { DialogModule } from '../../modules/dialog.module';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ProductService } from '../../service/product.service';
import { MensajeService } from '../../service/mensaje.service';
import { product } from '../../interface/product';

@Component({
  selector: 'app-dialog-product',
  imports: [DialogModule],
  templateUrl: './dialog-product.component.html',
  styleUrl: './dialog-product.component.css'
})
export class DialogProductComponent {
  constructor(private dialogRef: MatDialogRef<DialogProductComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,
    private fb: FormBuilder,
    private productService: ProductService,
    private mensaje: MensajeService) { }
  datosPrincipales: product;

  productForm: FormGroup;
  modo: string;
  parametro_busqueda: string;

  ngOnInit(): void {
    this.modo = this.data['Modo']
    this.datosPrincipales = this.data['tercero'];
    this.parametro_busqueda = this.data['parametro_busqueda'];
    this.form()
  }

  form() {
    this.productForm = this.fb.group({
      name: [this.datosPrincipales?.name, Validators.required],
      description: [this.datosPrincipales?.description, [Validators.required]],
      price: [this.datosPrincipales?.price, Validators.required],
      stock: [this.datosPrincipales?.stock, Validators.required],

    });

    if (this.modo == 'view') {
      this.productForm.disable()
    }
  }

  cerrar() {
    this.dialogRef.close(this.parametro_busqueda);
  }
  save() {
    if (this.productForm.valid) {
      const { id, name, description, price, stock } = this.productForm.value
      this.productService.createProduct({ id, name, description, price, stock }).subscribe(save => {
        this.mensaje.MostrarMensaje('Producto Creado Correctamente')
        this.cerrar();
      }, error => {
        this.mensaje.MostrarMensaje('Ha Ocurrido un error')
        this.cerrar();
      })
    }
  }
}
