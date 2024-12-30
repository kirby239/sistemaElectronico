import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CustomerComponent } from './components/customer/customer.component';
import { ProductComponent } from './components/product/product.component';
import { OrderComponent } from './components/order/order.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./shared/layout/layout.component').then((m) => m.LayoutComponent),
        children: [
            {
                path: 'home',
                loadComponent: () =>
                    import('./components/home/home.component').then((m) => m.HomeComponent)
            },
            {
                path: 'customer',
                loadComponent: () =>
                    import('./components/customer/customer.component').then((m) => m.CustomerComponent)
            },
            {
                path: 'product',
                loadComponent: () =>
                    import('./components/product/product.component').then((m) => m.ProductComponent)
            },
            {
                path: 'order',
                loadComponent: () =>
                    import('./components/order/order.component').then((m) => m.OrderComponent)
            },
            { path: '', redirectTo: '/home', pathMatch: 'full' },
        ]
    },
    { path: '**', component: PageNotFoundComponent }

    /*  { path: '', component: HomeComponent },
    { path: 'customer', component: CustomerComponent },
    { path: 'product', component: ProductComponent },
    { path: 'order', component: OrderComponent },
 */

];
