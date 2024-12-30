import { Component, ViewChild } from '@angular/core';
import { CustomerModule } from '../../modules/customer.module';
import { CustomerService } from '../../service/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { customer } from '../../interface/customer';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { DialogCustomerComponent } from '../../dialog/dialog-customer/dialog-customer.component';
import { product } from '../../interface/product';
import { ProductService } from '../../service/product.service';
import { DialogProductComponent } from '../../dialog/dialog-product/dialog-product.component';

@Component({
  selector: 'app-product',
  imports: [CustomerModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  displayedColumns: string[] = ['id', 'name', 'description', 'price', 'stock', 'acciones'];
  dataSource = new MatTableDataSource<product[]>;
  filteredProduct: any[];
  searchText: string = '';
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductService,
    private dialog: MatDialog) { }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  ngOnInit(): void {
    this.serviceInit()
  }

  serviceInit() {
    this.productService.getAllProduct().subscribe(list => {
      this.filteredProduct = list;
      this.crearTabla(list);
    })
  }
  crearTabla(data?: any[]) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.paginator.hidePageSize = false
    this.dataSource.sort = this.sort;
  }
  filterProduct(event?: any): void {
    if (event != undefined) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.searchText = filterValue
    }
    let search = this.filteredProduct;
    console.log(search);

    this.crearTabla(search.filter(option => option.id.toString().startsWith(this.searchText)
      || option.name.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option.description.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option.price.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option.stock.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
    ));
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }

  }

  openDialog(modo: string, data?: any) {
    const dialogCrear = this.dialog.open(DialogProductComponent, {
      width: '50%',
      height: 'auto',
      disableClose: true,
      autoFocus: false,
      data: {
        Modo: modo,
        tercero: data,
        parametro_busqueda: this.searchText
      }
    });
    dialogCrear.afterClosed().subscribe(data => {

      this.searchText = data;
      this.productService.getAllProduct().subscribe(list => {
        this.filteredProduct = list;
        this.createTableUsuario(list);
      })

    })

  }
  createTableUsuario(data: any) {
    this.crearTabla(data.filter(option => option?.id.toString().startsWith(this.searchText)
      || option?.name.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option?.description.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option?.price.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option?.stock.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
    ));
  }
}
