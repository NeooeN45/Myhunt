import { Injectable } from '@nestjs/common';
import { mapLayers, speciesCatalog } from '@cynetrace/contracts';

@Injectable()
export class CatalogService {
  getSpecies() {
    return speciesCatalog;
  }

  getMapLayers() {
    return mapLayers;
  }
}
