CREATE TABLE Producto(
	codigo character varying primary key,
	nombre character varying,
	descripcion character varying,
	precio int,
	cantidad int
);
CREATE TABLE Usuario(
	usuario character varying primary key,
	contrasenna character varying,
    isAdmin boolean
);  
CREATE TABLE Compra(
    num_compra serial primary key,
	usuario character varying references Usuario(usuario),
	producto character varying references Producto(codigo),
	cantidad int
);

CREATE TABLE HISTORIAL(
     sequencia serial primary key,
     num_compra integer references Compra(num_compra),
     fecha timestamp
);
alter table producto add column imagen integer;
alter table producto add column rating float ;



insert into usuario values('Admin','root',true);
insert into usuario values('Foo','root',false);
insert into usuario values('Goo','root',false);
insert into usuario values('Hoo','root',false);

insert into Producto values('0001','Vino','Del baratico',3000,10);
insert into Producto values('0002','Queso','Palmito',5000,5);
insert into Producto values('0003','PC','DELL',355000,3);

insert into producto values (
                '1',
                'Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)',
                '13.3 inch, Silver, 1.35 kg',
                900000,
                100,1,4.3);

insert into producto values (
                '2',
                'Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)',
                '14 inch, Gray, 1.659 kg',
                500000,
                100,2,4.3);

insert into producto values (
                '3',
                'Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)',
                '13.3 inch, Silver, 1.35 kg',
                450000,
                100,3,4.5);

insert into producto values (
                '4',
                'AMD E-Series 1.65 GHz, 2 GB RAM, 500 GB disco duro, Windows 7 Starter, HDMI',
                '14.3 inch, Black, 1.15 kg',
                250000,
                100,4,2.6);

insert into producto values (
                '5',
                'INTEL Dual Core 2 GHz, 4 GB RAM, 640 GB disco duro, Windows 7 Home Premium, HDMI',
                '14.0 inch, Black, 1.65 kg',
                350000,
                100,5,3.0);

insert into producto values (
                '6',
                'INTEL Dual Core 2.13 GHz, 4 GB RAM, 500 GB disco duro, Windows 7 Starter',
                '14.0 inch, Black, 1.25 kg',
                320000,
                100,6,4.1);

insert into producto values (
                '7',
                'AMD E-Series 1.65 GHz, 2 GB RAM, 500 GB disco duro, Windows 7 Starter, HDMI',
                '14.0 inch, Black, 1.15 kg',
                250000,
                100,7,4.6);

