package co.edu.uco.unidaddeportivaelbernabeu.bussines.assembler.entity;

public interface EntityDomainAssembler <D, E> {

    D ensamblarDominio(E entidad);

}
