import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { CustomerModule } from '../../modules/customer.module';
import { CustomerService } from '../../service/customer.service';
import { MatDialog } from '@angular/material/dialog';
import { customer } from '../../interface/customer';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { DialogCustomerComponent } from '../../dialog/dialog-customer/dialog-customer.component';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CustomerModule],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  displayedColumns: string[] = ['id', 'name', 'email', 'createdAt', 'acciones'];
  dataSource = new MatTableDataSource<customer[]>;
  filteredCustomer: any[];
  searchText: string = '';
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private customerService: CustomerService,
    private dialog: MatDialog,
  ) { }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  ngOnInit(): void {
    this.serviceInit()
  }
  serviceInit() {
    this.customerService.getAllCustomers().subscribe(list => {
      this.filteredCustomer = list;
      this.crearTabla(list);
    })
  }
  crearTabla(data?: any[]) {
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.paginator = this.paginator;
    this.paginator.hidePageSize = false
    this.dataSource.sort = this.sort;
  }

  filterCustomer(event?: any): void {
    if (event != undefined) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.searchText = filterValue
    }
    let search = this.filteredCustomer;
    console.log(search);

    this.crearTabla(search.filter(option => option.id.toString().startsWith(this.searchText)
      || option.name.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option.email.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
    ));
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }

  }
  openDialog(modo: string, data?: any) {
    const dialogCrear = this.dialog.open(DialogCustomerComponent, {
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
      this.customerService.getAllCustomers().subscribe(list => {
        this.filteredCustomer = list;
        this.createTableUsuario(list);
      })

    })

  }
  createTableUsuario(data: any) {
    this.crearTabla(data.filter(option => option?.id.toString().startsWith(this.searchText)
      || option?.name.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
      || option?.email.toString().toLowerCase().includes(this.searchText.toString().toLowerCase())
    ));
  }
}
