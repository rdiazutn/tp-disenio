import User from '../services/User'
import Egreso from '../services/Egreso'
import Item from '../services/Item'
import Categoria from '../services/Categoria'
import ValidationRule from '../services/ValidationRule'

export default function ({ app }, inject) {
  const user = new User({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('userService', user)
  const egreso = new Egreso({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('egresoService', egreso)
  const item = new Item({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('itemService', item)
  const categoria = new Categoria({ $axios: app.$axios, $cookiz: app.$cookiz, $store: app.store })
  inject('categoriaService', categoria)
  const validationRule = new ValidationRule(app)
  inject('rl', validationRule)
}
